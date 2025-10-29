package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "bookList", urlPatterns = "")
public class BookListServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final BookService bookService;
    private final BookReservationService reservationService;

    public BookListServlet(BookService bookService, SpringTemplateEngine templateEngine, BookReservationService reservationService) {
        this.bookService = bookService;
        this.templateEngine = templateEngine;
        this.reservationService = reservationService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        context.setVariable("ipAddress", req.getRemoteAddr());
        context.setVariable("books", this.bookService.listAll());

        templateEngine.process("listbooks.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String readerName = req.getParameter("readerName"),
                readerAddress = req.getParameter("readerAddress"),
                bookTitle = req.getParameter("selectedBook");
        long numCopies = Integer.parseInt(req.getParameter("numCopies"));

        BookReservation reservation = reservationService.placeReservation(bookTitle, readerName, readerAddress, numCopies);

        req.getSession().setAttribute("reservation", reservation);

        resp.sendRedirect("/bookReservation?reservation="+reservation.hashCode());
    }
}
