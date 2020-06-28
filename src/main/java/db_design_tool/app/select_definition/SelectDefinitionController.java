package db_design_tool.app.select_definition;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_design_tool.domain.model.DataTypeMaster;
import db_design_tool.domain.model.FieldSourceDefinition;
import db_design_tool.domain.model.SelectDefinition;
import db_design_tool.domain.model.TableMaster;
import db_design_tool.domain.model.TableSourceDefinition;
import db_design_tool.domain.service.data_type_master.DataTypeMasterService;
import db_design_tool.domain.service.data_type_master.DataTypeMasterServiceImpl;
import db_design_tool.domain.service.select_definition.SelectDefinitionService;
import db_design_tool.domain.service.select_definition.SelectDefinitionServiceImpl;

/**
 * テーブル定義編集画面を管理する。
 */
@WebServlet("/select_definition")
public class SelectDefinitionController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final SelectDefinitionHelper helper;
    private final SelectDefinitionValidator validator;
    private final SelectDefinitionService selectDefinitionService;
    private final DataTypeMasterService dataTypeMasterService;

    public SelectDefinitionController() throws Exception {
        super();
        helper = new SelectDefinitionHelper();
        validator = new SelectDefinitionValidator();
        selectDefinitionService = new SelectDefinitionServiceImpl();
        dataTypeMasterService = new DataTypeMasterServiceImpl();
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
        request.setAttribute("entityType", 2);

        try {
            List<DataTypeMaster> dataTypeList = dataTypeMasterService.findAll();
            request.setAttribute("dataTypeList", dataTypeList);
        } catch (Exception e) {
            throw new IOException(e);
        }

        try {
            final List<TableMaster> tableMasterList = selectDefinitionService
                    .findEntityAll();
            request.setAttribute("tableMasterList", tableMasterList);
        } catch (Exception e) {
            throw new IOException(e);
        }

        if (tableId != null && !tableId.isEmpty()) {
            request.setAttribute("tableId", tableId);
            Map<String, String[]> paramMap = request.getParameterMap();

            try {
                if (!paramMap.containsKey("tableMaster.physicalName")) {
                    final SelectDefinition selectDefinition = selectDefinitionService
                            .findSelectDefinitionByTableId(
                                    Integer.parseInt(tableId));

                    request.setAttribute("tableMaster",
                            selectDefinition.getTableMaster());
                    request.setAttribute("tableSourceDefinitionArray",
                            selectDefinition.getTableSourceDefinition());
                    request.setAttribute("fieldSourceDefinitionArray",
                            selectDefinition.getFieldSourceDefinition());
                }
            } catch (final Exception e) {
                throw new IOException(e);
            }
        }

        final ServletContext context = getServletContext();
        final RequestDispatcher dispatcher = context
                .getRequestDispatcher("/WEB-INF/select_definition/index.jsp");
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
        // 画面から送信されたフォームデータをモデルへ変換
        final SelectDefinition selectDefinition = new SelectDefinition();

        final TableMaster tableMaster = helper.convertToTableMaster(request);
        selectDefinition.setTableMaster(tableMaster);

        final TableSourceDefinition[] tableSourceDefinitionArray = helper
                .convertToTableSourceDefinition(request);
        selectDefinition.setTableSourceDefinition(tableSourceDefinitionArray);

        final FieldSourceDefinition[] fieldSourceDefinitionArray = helper
                .convertToFieldSourceDefinition(request);
        selectDefinition.setFieldMaster(fieldSourceDefinitionArray);
        selectDefinition.setFieldSourceDefinition(fieldSourceDefinitionArray);

        // バリデーションチェック
        boolean hasError = false;

        if (validator.validateTableMaster(tableMaster)) {
            hasError = true;
        }

        if (tableSourceDefinitionArray != null) {
            for (final TableSourceDefinition tableSourceDefinition : tableSourceDefinitionArray) {
                if (validator
                        .validateTableSourceDefinition(tableSourceDefinition)) {
                    hasError = true;
                }
            }
        } else {
            hasError = true;
            request.setAttribute("tableSourceDefinitionError",
                    "結合条件を入力してください。");
        }

        if (fieldSourceDefinitionArray != null) {
            for (final FieldSourceDefinition fieldSourceDefinition : fieldSourceDefinitionArray) {
                if (validator
                        .validateFieldSourceDefinition(fieldSourceDefinition)) {
                    hasError = true;
                }
            }
        } else {
            hasError = true;
            request.setAttribute("fieldSourceDefinitionError",
                    "クエリ定義を入力してください。");
        }

        // 登録済みクエリ定義と画面から送信されたフォームデータをマージする。
        if (tableMaster.getTableId() > 0) {
            try {
                SelectDefinition savedSelectDefinition = selectDefinitionService
                        .findSelectDefinitionByTableId(
                                tableMaster.getTableId());

                if (tableSourceDefinitionArray != null) {
                    selectDefinition.setTableSourceDefinition(
                            helper.mergeTableSourceDefinition(
                                    savedSelectDefinition
                                            .getTableSourceDefinition(),
                                    tableSourceDefinitionArray));
                }

                if (fieldSourceDefinitionArray != null) {
                    selectDefinition.setFieldSourceDefinition(
                            helper.mergeFieldSourceDefinition(
                                    savedSelectDefinition
                                            .getFieldSourceDefinition(),
                                    fieldSourceDefinitionArray));
                    selectDefinition.setFieldMaster(
                            selectDefinition.getFieldSourceDefinition());
                }
            } catch (Exception e) {
                throw new IOException(e);
            }
        }

        // 画面へデータを送信する。
        request.setAttribute("tableMaster", tableMaster);
        request.setAttribute("tableSourceDefinitionArray",
                tableSourceDefinitionArray);
        request.setAttribute("fieldSourceDefinitionArray",
                fieldSourceDefinitionArray);

        int deleteFlag = Integer
                .parseInt(request.getParameter("tableMaster.deleteFlag"));

        if (deleteFlag == 1) {
            try {
                selectDefinitionService
                        .deleteByTableId(tableMaster.getTableId());
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
                    selectDefinitionService.update(selectDefinition);
                } else {
                    selectDefinitionService.create(selectDefinition);
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
