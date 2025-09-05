package vn.iotstar.controllers.admin;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.models.CategoryModel;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;
import vn.iotstar.utils.UploadUtils;
import vn.iotstar.utils.path.Constant;

@WebServlet(urlPatterns = { "/admin/category/edit" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 	// 2MB
				 maxFileSize = 1024 * 1024 * 10,  		// 10MB
				 maxRequestSize = 1024 * 1024 * 50)		// 50MB
public class CategoryEditController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ICategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		CategoryModel category = cateService.findById(Integer.parseInt(id));
		req.setAttribute("category", category);
		req.getRequestDispatcher("/views/admin/edit-category.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		try {
			CategoryModel category = new CategoryModel();

			// Lấy dữ liệu từ form
			category.setCateid(Integer.parseInt(req.getParameter("id")));
			category.setCatename(req.getParameter("name"));

			// Lấy file icon từ form
			Part filePart = req.getPart("icon");

			// Gọi UploadUtils để xử lý upload
			String iconPath = UploadUtils.processUploadFile(filePart, Constant.DIR + "/category");

			// Nếu có icon thì set
			if (iconPath != null) {
				category.setIcon(iconPath);
			}

			// Cập nhật dữ liệu
			cateService.update(category);

			// Redirect về danh sách
			resp.sendRedirect(req.getContextPath() + "/admin/category/list");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Lỗi cập nhật danh mục.");
			req.getRequestDispatcher("/views/admin/edit-category.jsp").forward(req, resp);
		}
	}

	// Helper method để lấy tên file gốc
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] tokens = contentDisp.split(";");
		for (String token : tokens) {
			if (token.trim().startsWith("filename")) {
				return token.substring(token.indexOf("=") + 2, token.length() - 1).replace("\\", "/");
			}
		}
		return null;
	}
}
