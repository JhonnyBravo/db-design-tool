package db_design_tool.app.table_definition;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import db_design_tool.domain.model.FieldMaster;
import db_design_tool.domain.model.TableDefinition;
import db_design_tool.domain.model.TableMaster;

/**
 * Servlet implementation class TableDefinitionController
 */
@WebServlet("/table_definition")
public class TableDefinitionController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final TableDefinitionHelper helper;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TableDefinitionController() {
        super();
        helper = new TableDefinitionHelper();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        final ServletContext context = getServletContext();
        final RequestDispatcher dispatcher = context
                .getRequestDispatcher("/WEB-INF/table_definition/index.jsp");
        dispatcher.include(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
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

        final Validator validator = Validation.buildDefaultValidatorFactory()
                .getValidator();
        boolean hasError = false;

        final Set<ConstraintViolation<TableMaster>> tableMasterViolation = validator
                .validate(tableMaster);

        if (tableMasterViolation.size() > 0) {
            hasError = true;

            tableMasterViolation.stream().forEach(e -> {
                final String path = e.getPropertyPath().toString();

                if (path.equals("physicalName")) {
                    tableMaster.setPhysicalNameError(e.getMessage());
                } else if (path.equals("logicalName")) {
                    tableMaster.setLogicalNameError(e.getMessage());
                }
            });
        }

        request.setAttribute("tableMaster", tableMaster);

        if (fieldMasterArray != null) {
            for (final FieldMaster fieldMaster : fieldMasterArray) {
                final Set<ConstraintViolation<FieldMaster>> fieldMasterViolation = validator
                        .validate(fieldMaster);

                if (fieldMasterViolation.size() > 0) {
                    hasError = true;

                    fieldMasterViolation.stream().forEach(e -> {
                        final String path = e.getPropertyPath().toString();

                        if (path.equals("physicalName")) {
                            fieldMaster.setPhysicalNameError(e.getMessage());
                        } else if (path.equals("logicalName")) {
                            fieldMaster.setLogicalNameError(e.getMessage());
                        } else if (path.equals("description")) {
                            fieldMaster.setDescriptionError(e.getMessage());
                        }
                    });
                }
            }
        } else {
            hasError = true;
            request.setAttribute("fieldMasterError", "フィールド定義を入力してください。");
        }

        request.setAttribute("fieldMasterArray", fieldMasterArray);

        doGet(request, response);
    }

}
