package db_design_tool.app.table_definition;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_design_tool.domain.model.FieldMaster;
import db_design_tool.domain.model.TableDefinition;
import db_design_tool.domain.model.TableMaster;
import db_design_tool.domain.service.table_definition.TableDefinitionService;
import db_design_tool.domain.service.table_definition.TableDefinitionServiceImpl;

/**
 * テーブル定義編集画面を管理する。
 */
@WebServlet("/table_definition")
public class TableDefinitionController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final TableDefinitionHelper helper;
    private final TableDefinitionValidator validator;
    private final TableDefinitionService service;

    public TableDefinitionController() throws Exception {
        super();
        helper = new TableDefinitionHelper();
        validator = new TableDefinitionValidator();
        service = new TableDefinitionServiceImpl();
    }

    /**
     * テーブル定義入力フォームの初期表示を管理する。 クエリパラメータに tableId が指定されている場合は
     * 登録済みテーブル定義をフォームへ出力する。 指定されていない場合は空のフォームを表示する。
     *
     * @param request
     *            {@link javax.servlet.http.HttpServletRequest}
     * @param response
     *            {@link javax.servlet.http.HttpServletResponse}
     * @throws ServletException
     *             {@link javax.servlet.ServletException}
     * @throws IOException
     *             {@link java.io.IOException}
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        final String tableId = request.getParameter("tableId");
        request.setAttribute("entityType", 1);

        if (tableId != null && !tableId.isEmpty()) {
            request.setAttribute("tableId", tableId);
            Map<String, String[]> paramMap = request.getParameterMap();

            try {

                if (!paramMap.containsKey("tableMaster.physicalName")) {
                    final TableDefinition tableDefinition = service
                            .findTableDefinitionByTableId(
                                    Integer.parseInt(tableId));

                    request.setAttribute("tableMaster",
                            tableDefinition.getTableMaster());
                    request.setAttribute("fieldMasterArray",
                            tableDefinition.getFieldMaster());
                }
            } catch (final Exception e) {
                throw new IOException(e);
            }
        }

        final ServletContext context = getServletContext();
        final RequestDispatcher dispatcher = context
                .getRequestDispatcher("/WEB-INF/table_definition/index.jsp");
        dispatcher.include(request, response);
    }

    /**
     * テーブル定義の登録処理を管理する。 tableId が指定されていない場合は新規登録を実行し、 指定されている場合は更新を実行する。
     *
     * @param request
     *            {@link javax.servlet.http.HttpServletRequest}
     * @param response
     *            {@link javax.servlet.http.HttpServletResponse}
     * @throws ServletException
     *             {@link javax.servlet.ServletException}
     * @throws IOException
     *             {@link java.io.IOException}
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        final TableMaster tableMaster = helper.convertToTableMaster(request);
        final FieldMaster[] fieldMasterArray = helper
                .convertToFieldMaster(request);

        final TableDefinition tableDefinition = new TableDefinition();
        tableDefinition.setTableMaster(tableMaster);
        tableDefinition.setFieldMaster(fieldMasterArray);

        boolean hasError = false;

        if (validator.validateTableMaster(tableMaster)) {
            hasError = true;
        }

        request.setAttribute("tableMaster", tableMaster);

        if (fieldMasterArray != null) {
            for (final FieldMaster fieldMaster : fieldMasterArray) {
                if (validator.validateFieldMaster(fieldMaster)) {
                    hasError = true;
                }
            }

            if (tableMaster.getTableId() > 0) {
                try {
                    final TableDefinition savedTableDefinition = service
                            .findTableDefinitionByTableId(
                                    tableMaster.getTableId());
                    tableDefinition.setFieldMaster(helper.mergeFieldMaster(
                            savedTableDefinition.getFieldMaster(),
                            fieldMasterArray));
                } catch (final Exception e) {
                    throw new IOException(e);
                }
            }
        } else {
            hasError = true;
            request.setAttribute("fieldMasterError", "フィールド定義を入力してください。");
        }

        request.setAttribute("fieldMasterArray", fieldMasterArray);

        int deleteFlag = Integer
                .parseInt(request.getParameter("tableMaster.deleteFlag"));

        if (deleteFlag == 1) {
            try {
                service.deleteByTableId(tableMaster.getTableId());
                response.sendRedirect("/db-design-tool/home");
                return;
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "データの削除に失敗しました。");
                doGet(request, response);
                return;
            }
        }

        if (hasError) {
            doGet(request, response);
        } else {
            try {
                if (tableMaster.getTableId() > 0) {
                    service.update(tableDefinition);
                } else {
                    service.create(tableDefinition);
                }

                response.sendRedirect("/db-design-tool/home");
            } catch (final Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "データの保存に失敗しました。");
                doGet(request, response);
            }
        }
    }
}
