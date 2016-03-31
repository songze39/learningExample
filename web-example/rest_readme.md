#rest说明:jersey帮助
##jersey常用注解解释
<table  border="1">
  <tr>
    	<td>Annotation</td>
     	<td>作用</td>
      	<td>说明</td>
  </tr>
  <tr>
    	<td>@GET</td>
     	<td>查询请求</td>
      	<td>相当于http的get请求</td>
  </tr>
   <tr>
    	<td>@POST</td>
     	<td>插入请求</td>
      	<td>相当于http的post请求</td>
  </tr>
  <tr>
     <td>@PUT</td>
     <td>更新请求</td>
     <td>相当于数据库的更新请求</td>
  </tr>
  <tr>
	  <td>@DELETE</td>
	  <td>删除请求</td>
	  <td>相当于数据的删除数据操作</td>
  </tr>
  <tr>
	  <td>@Path</td>
	  <td>uri路径</td>
	  <td>定义资源的访问路径，client通过这个路径访问资源。比如：@Path("user")</td>
  </tr>
  <tr>
	  <td>@Produces</td>
	  <td>指定返回MIME格式</td>
	  <td>资源按照那种数据格式返回，可取的值有：MediaType.APPLICATION_XXX。比如：@Produces(MediaType.APPLICATION_XML)</td>
  </tr>
  <tr>
	  <td>@Consumes</td>
	  <td>接受指定的MIME格式</td>
	  <td>只有符合这个参数设置的请求再能访问到这个资源。比如@Consumes("application/x-www-form-urlencoded")</td>
  </tr>
  <tr>
	  <td>@PathParam</td>
	  <td>uri路径参数</td>
	  <td>写在方法的参数中，获得请求路径参数。比如：@PathParam("username")  String userName</td>
  </tr>
  <tr>
	  <td>@QueryParam</td>
	  <td>uri路径请求参数</td>
	  <td>写在方法的参数中，获得请求路径附带的参数。比如：@QueryParam("desc")  String desc</td>
  </tr>
  <tr>
	  <td>@DefaultValue</td>
	  <td>设置@QueryParam参数的默认值</td>
	  <td>如果@QueryParam没有接收到值，就使用默认值。比如：@DefaultValue("description") @QueryParam("desc") String desc</td>
  </tr>
  <tr>
  	   <td>@FormParam</td>
  	   <td>form传递的参数</td>
  	   <td>接受form传递过来的参数。比如：@FormParam("name")  String userName</td>
  </tr>
  <tr>
  	   <td>@BeanParam</td>
  	   <td>通过bean的形式传递参数</td>
  	   <td>接受client传递的bean类型的参数，同时这个bean可以在属性上配置@FormParam用以解决client的属性名称和bean的属性名称不一致的问题。比如：@BeanParam  			User user</td>
  </tr>
  <tr>
  	   <td>@Context</td>
  	   <td>获得一些系统环境信息</td>
  	   <td>通过@Context可以获得以下信息：UriInfo、ServletConfig、ServletContext、HttpServletRequest、HttpServletResponse和HttpHeaders等</td>
  </tr>
  <tr>
  	   <td>@XmlRootElement</td>
  	   <td>将bean转换为xml</td>
  	   <td>如果要讲bean以xml或json的格式返回，必须要这个注解。比如：@XmlRootElement public class User｛...｝</td>
  </tr>
   <tr>
  	   <td>@XmlElements</td>
  	   <td></td>
  	   <td></td>
  </tr>
  <tr>
  	   <td>@XmlElement</td>
  	   <td></td>
  	   <td></td>
  </tr>
</table>