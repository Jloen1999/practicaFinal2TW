<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="es.unex.cum.tw.models.Libro" %>

<div class="container-md">
    <section class="row mt-3">
        <% if (application.getAttribute("books") != null) {
            List<Libro> books = (List<Libro>) application.getAttribute("books");
            for (Libro book : books) {%>
        <div class="card bg-dark text-white m-auto col-md-6 col-lg-4 col-xl-3 mt-3 me-3"
             style="width: 15rem; height: 25rem;">
            <img src="<%=book.getUrlImg()%>" class="card-img-top m-auto w-50"
                 alt="Imagen del libro <%=book.getTitulo()%>">
            <div class="card-body">
                <h5 class="card-title"><%=book.getTitulo()%>
                </h5>
                <p class="card-text"><%=book.getDescripcion()%>
                </p>
                <div class="d-flex g-col-2"><a href="#"
                                               class="btn btn-primary position-absolute bottom-0 start-0"
                                               data-bs-toggle="modal"
                                               data-bs-target="#exampleModal<%=book.getIdLibro()%>" type="button">Ver
                    detalles</a>
                    <% if (session.getAttribute("user") != null && session.getAttribute("admin") == null) {%>
                    <a href="${pageContext.request.contextPath}/reservarLibro?id=<%=book.getIdLibro()%>"
                       class="btn btn-primary position-absolute bottom-0 end-0" type="button">Reservar</a>
                    <%}%></div>
                <div class="modal fade" id="exampleModal<%=book.getIdLibro()%>" tabindex="-1"
                     aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5 text-dark text-center"
                                    id="exampleModalLabel"><%=book.getTitulo()%>
                                </h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <table class="table table-dark table-striped">
                                    <tbody>
                                    <tr>
                                        <th scope="row">Autor</th>
                                        <td><%=book.getAutor()%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Temática</th>
                                        <td><%=book.getTematica()%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">ISBN</th>
                                        <td><%=book.getIsbn()%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Resumen</th>
                                        <td><%=book.getDescripcion()%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Valoración</th>
                                        <td><% out.print(book.getPuntuacion() + " ");
                                            for (int i = 0; i < (int) (book.getPuntuacion()); i++) {
                                                out.print("<i class='bi bi-star-fill text-warning'></i>");
                                            }
                                            for (int i = 0; i < 5 - (int) book.getPuntuacion(); i++) {
                                                if (book.getPuntuacion() % 1 == 0.5 && i ==  0) {
                                                    out.print("<i class='bi bi-star-half text-warning'></i>");
                                                } else {
                                                    out.print("<i class='bi bi-star'></i>");
                                                }
                                            }%>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                                <%
                                    if (session.getAttribute("user") != null) {%>
                                <div class="d-flex justify-content-between"><a
                                        href="${pageContext.request.contextPath}/reservarLibro?id=<%=book.getIdLibro()%>"
                                        class="btn btn-primary">Reservar</a>
                                    <a href="${pageContext.request.contextPath}/eliminarReservaLibro?id=<%=book.getIdLibro()%>"
                                       class="btn btn-primary">Eliminar reserva</a></div>
                                <%}%>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%} %>
        <%} else {%>
        <div class="alert alert-danger" role="alert">
            No hay libros disponibles
        </div>
        <% } %>


    </section>
</div>
