<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<meta charset="utf-8">
</head>

<body>
	<div class="novo-projeto" align="left">
		<div class="form" align="center">
			<h2>Cadastrar Projeto</h2>
			 <form action="<c:url value="/projetos/new" />" method="POST" class="form-horizontal" id="add-projeto-form">

				<table>
					<tr>
						<td>Nome do Projeto:</td>
						<td><input type='text' name='projeto' value=''></td>
					</tr>
					<tr>
						<td>Data de Inicio:</td>
						<td><input type="date" name='datainicio' /></td>
					</tr>
					<tr>
						<td>Data de Término:</td>
						<td><input type="date" name='datatermino' value=''></td>
					</tr>
					<tr>
						<td>Descrição do Projeto:</td>
						<td><input type='text' name='descricao' /></td>
					</tr>
					<tr>
						<td>Atividades:</td>
						<td><input type='text' name='atividades' value=''></td>
					</tr>
					<tr>
						<td>Número de Bolsas:</td>
						<td><input type="number" name='qtdbolsas' /></td>
					</tr>
					<tr>
						<td>Local do Projeto:</td>
						<td><input type='text' name='local' value=''></td>
					</tr>
					<tr>
						<td>Participantes:</td>
						<td><input type='text' name='participantes' /></td>
					</tr>
					<tr>
						<td><input name="reset" type="reset" value="Limpar Campos" />
						</td>
						<td><input name="submit" type="submit"
							value="Cadastrar Projeto" /></td>
					</tr>
				</table>

			</form>
		</div>
	</div>
</body>

</html>