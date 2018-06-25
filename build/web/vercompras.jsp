<%-- 
    Document   : compras
    Created on : Jun 21, 2018, 11:48:51 PM
    Author     : Sary
--%>

<%@page import="dao.CompraDAO"%>
<%@page import="modelo.Compra"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="modelo.Producto"%>
<%@page import="dao.ProductoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Ver Compras</title>

        <!-- Bootstrap Core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

        <!-- DataTables CSS -->
        <link href="vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

        <!-- DataTables Responsive CSS -->
        <link href="vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="dist/css/sb-admin-2.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>

    <body>

        <div id="wrapper">

            <!-- Navigation -->
            <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="inventario.jsp">Almacén</a>
                </div>
                <!-- /.navbar-header -->

                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="login.jsp"><i class="fa fa-sign-out fa-fw"></i> Cerrar Sesión </a>
                            </li>
                        </ul>
                        <!-- /.dropdown-user -->
                    </li>
                    <!-- /.dropdown -->
                </ul>
                <!-- /.navbar-top-links -->

                <div class="navbar-default sidebar" role="navigation">
                    <div class="sidebar-nav navbar-collapse">
                        <ul class="nav" id="side-menu">

                            <li>
                                <a href="inventario.jsp"><i class="fa fa-archive fa-fw"></i> Inventario</a>
                            </li>
                            <li>
                                <a href="registrodiario.jsp"><i class="fa fa-money fa-fw"></i> Registro Diario</a>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-edit fa-fw"></i> Productos<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="crearproducto.jsp">Crear</a>
                                    </li>
                                    <li>
                                        <a href="modificarproducto.jsp">Modificar</a>
                                    </li>
                                    <li>
                                        <a href="eliminarproducto.jsp">Eliminar</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-table fa-fw"></i> Compras<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="registrarcompras.jsp">Registrar Compras</a>
                                    </li>
                                    <li>
                                        <a href="vercompras.jsp">Ver Compras</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Ventas<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="registrarventas.jsp">Registrar Ventas</a>
                                    </li>
                                    <li>
                                        <a href="verventas.jsp">Ver Ventas</a>
                                    </li>
                            </li>
                        </ul>
                        <li>
                            <a href="#"><i class="fa fa-minus-circle fa-fw"></i> Gastos<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="registrargastos.jsp">Registrar Gastos</a>
                                </li>
                                <li>
                                    <a href="vergastos.jsp">Ver Gastos</a>
                                </li>
                        </li>
                        </ul>

                    </div>
                    <!-- /.sidebar-collapse -->
                </div>

            </nav>

            <!-- Page Content -->
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Ver Compras</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Compras de mercancía:
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>Código Compra</th>
                                            <th>Fecha</th>
                                            <th>Producto</th>
                                            <th>Cantidad</th>
                                            <th>Costo Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <% 
                                            CompraDAO compradao = new CompraDAO();
                                            ArrayList<Compra> compras = compradao.getAllCompras();
                                                            
                                            Locale.setDefault(Locale.US);
                                            DecimalFormat num = new DecimalFormat("#,###.00");  
                                            
                                            for(int i = 0; i<compras.size(); i++) {
                                                
                                                int idCompra = (compras.get(i)).getIdCompra();
                                                
                                                Date date = (compras.get(i)).getFecha(); 
                                                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                                String fecha = df.format(date);

                                                String nombre = (compras.get(i)).getNombre();
                                                
                                                float cantidad = (compras.get(i)).getCantidad();
                                                String cantidads = num.format(cantidad);
                                                float total = (compras.get(i)).getTotal();
                                                String totals = num.format(total);

                                        %>

                                        <tr class="odd gradeA">
                                            <td><%=idCompra%></td>
                                            <td><%=fecha%></td>
                                            <td><%=nombre%></td>
                                            <td><%=cantidads%></td>  
                                            <td><%=totals%></td> 
                                        </tr>


                                        <%
                                            }
                                        %>

                                    </tbody>
                                </table>
                                <!-- /.table-responsive -->


                                <br>

                                <div id="botones">
                                    <button id="modificar" onclick="modificar()" class="btn btn-success">Modificar Compra</button>
                                    <button id="eliminar" onclick="eliminar()" class="btn btn-success">Eliminar Compra</button>
                                </div>

                                <br>

                                <!--Modificar-->

                                <div id="seccionmod" class="panel panel-default" style="display: none;">
                                    <div class="panel-heading">
                                        Modificar Compra:
                                    </div>

                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-lg-6">
                                                <form role="form" action="ModificarCompras" method="POST">
                                                    <div class="form-group">
                                                        <label>Código de compra a modificar</label>

                                                        <select class="form-control" name="opcion">

                                                            <%  
                                                                for(int i = 0; i<compras.size(); i++) {
                                                                    int opcion = (compras.get(i)).getIdCompra();                                                                
                                                            %>

                                                            <option> <%=opcion %> </option>

                                                            <%
                                                                }
                                                            %>

                                                        </select>

                                                    </div>

                                                    <div class="form-group">
                                                        <label> Fecha (*)</label>
                                                        <input type="date" class="form-control" name="fecha" placeholder="Producto...">
                                                    </div>

                                                    <div class="form-group">
                                                        <label>Producto (*)</label>
                                                        <select class="form-control" name="producto" required>

                                                            <% 
                                                                ProductoDAO prodao = new ProductoDAO();
                                                                ArrayList<Producto> productos = prodao.getAllProductos();
                                                            
                                                                for(int i = 0; i<productos.size(); i++) {
                                                                String opcion = (productos.get(i)).getNombre();
                                                            %>

                                                            <option> <%=opcion %> </option>

                                                            <%
                                                                }
                                                            %>

                                                        </select>
                                                    </div>

                                                    <div class="form-group">
                                                        <label> Nueva Cantidad (*)</label>
                                                        <input class="form-control" name="cantidad" placeholder="Nueva cantidad...">
                                                    </div>

                                                    <button type="submit" class="btn btn-success">Modificar</button>
                                                    <button type="button" class="btn btn-danger" onclick="cancelar();">Cancelar</button>

                                                </form>

                                            </div>
                                        </div>
                                    </div>

                                </div>

                                <!--Eliminar-->

                                <div id="seccionelim" class="panel panel-default" style="display: none;">
                                    <div class="panel-heading">
                                        Eliminar Compra:
                                    </div>

                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-lg-6">
                                                <form role="form" action="EliminarCompra" method="POST">
                                                    <div class="form-group">
                                                        <label>Código de compra a eliminar</label>

                                                        <select class="form-control" name="opcion">

                                                            <%  
                                                                for(int i = 0; i<compras.size(); i++) {
                                                                    int opcion = (compras.get(i)).getIdCompra();                                                                
                                                            %>

                                                            <option> <%=opcion %> </option>

                                                            <%
                                                                }
                                                            %>

                                                        </select>

                                                    </div>

                                                    <button type="submit" class="btn btn-success">Eliminar</button>
                                                    <button type="button" class="btn btn-danger" onclick="cancelar();">Cancelar</button>

                                                </form>

                                            </div>
                                        </div>
                                    </div>

                                </div>                           


                            </div>
                            <!-- /.panel-body -->
                        </div>
                        <!-- /.panel -->
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->

            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

        <!-- jQuery -->
        <script src="vendor/jquery/jquery.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

        <!-- Metis Menu Plugin JavaScript -->
        <script src="vendor/metisMenu/metisMenu.min.js"></script>

        <!-- DataTables JavaScript -->
        <script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
        <script src="vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
        <script src="vendor/datatables-responsive/dataTables.responsive.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="dist/js/sb-admin-2.js"></script>

        <!-- Page-Level Demo Scripts - Tables - Use for reference -->
        <script>
                                                        $(document).ready(function () {
                                                            $('#dataTables-example').DataTable({
                                                                responsive: true
                                                            });
                                                        });
        </script>

        <script>
            function cancelar() {
                //location.reload();
                document.getElementById('seccionmod').style.display = 'none';
                document.getElementById('seccionelim').style.display = 'none';
                document.getElementById('botones').style.display = 'block';
            }
        </script>

        <script>
            function modificar() {
                document.getElementById('seccionmod').style.display = 'block';
                document.getElementById('botones').style.display = 'none';
            }
        </script>

        <script>
            function eliminar() {
                document.getElementById('seccionelim').style.display = 'block';
                document.getElementById('botones').style.display = 'none';
            }
        </script>

    </body>

</html>

