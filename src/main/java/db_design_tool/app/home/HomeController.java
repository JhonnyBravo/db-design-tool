package db_design_tool.app.home;

import db_design_tool.domain.model.TableMaster;
import db_design_tool.domain.service.table_definition.TableDefinitionService;
import db_design_tool.domain.service.table_definition.TableDefinitionServiceImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ホーム画面を管理する。
 */
@WebServlet("/definition")
public class HomeController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private final TableDefinitionService service;

  public HomeController() throws Exception {
    super();
    service = new TableDefinitionServiceImpl();
  }

  /**
   * ホーム画面へ登録済みのテーブル名とクエリ名の一覧を送信する。
   *
   * @param request {@link javax.servlet.http.HttpServletRequest}
   * @param response {@link javax.servlet.http.HttpServletResponse}
   * @throws ServletException {@link javax.servlet.ServletException}
   * @throws IOException {@link java.io.IOException}
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      final List<TableMaster> tableList = service.findTableByEntityType(1);
      request.setAttribute("tableList", tableList);

      final List<TableMaster> queryList = service.findTableByEntityType(2);
      request.setAttribute("queryList", queryList);

      final ServletContext context = getServletContext();
      final RequestDispatcher dispatcher = context.getRequestDispatcher("/WEB-INF/home/index.jsp");
      dispatcher.include(request, response);
    } catch (final Exception e) {
      throw new IOException(e);
    }
  }
}
