package db_design_tool.app.table_definition;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import db_design_tool.domain.model.FieldMaster;
import db_design_tool.domain.model.TableDefinition;
import db_design_tool.domain.service.table_definition.TableDefinitionService;
import db_design_tool.domain.service.table_definition.TableDefinitionServiceImpl;

/**
 * テーブル定義を JSON 形式でクライアントへ送信する。
 */
@WebServlet("/data/table")
public class TableDefinitionAjaxController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final Gson gson;
    private final TableDefinitionService service;

    public TableDefinitionAjaxController() throws Exception {
        super();
        gson = new Gson();
        service = new TableDefinitionServiceImpl();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        final String tableId = request.getParameter("tableId");
        final String fieldId = request.getParameter("fieldId");

        String json = null;

        try {
            if (tableId != null && !tableId.isEmpty()) {
                // table_id をキーに TableDefinition を取得する。
                TableDefinition tableDefinition = service
                        .findTableDefinitionByTableId(
                                Integer.parseInt(tableId));
                json = gson.toJson(tableDefinition);
            } else if (fieldId != null && !fieldId.isEmpty()) {
                // field_id をキーに FieldMaster を取得する。
                FieldMaster fieldMaster = service
                        .findFieldByFieldId(Integer.parseInt(fieldId));
                json = gson.toJson(fieldMaster);
            }
        } catch (Exception e) {
            throw new IOException(e);
        }

        response.getWriter().println(json);
    }
}
