package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.BookReservationRepository;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "bookReservationPage", urlPatterns = "/bookReservation")
public class BookReservationServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final BookReservationRepository bookReservationRepository;

    public BookReservationServlet(BookReservationRepository bookReservationRepository, SpringTemplateEngine templateEngine) {
        this.bookReservationRepository = bookReservationRepository;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

//        BookReservation reservation = bookReservationRepository;

        getServletContext().setAttribute("readerName", "nameHere");
        getServletContext().setAttribute("clientIP", req.getRemoteAddr());
        getServletContext().setAttribute("reservationBookTitle", "yeah");
        getServletContext().setAttribute("numCopies", 23);

        templateEngine.process("reservationConfirmation.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
