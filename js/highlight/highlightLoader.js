eval(function()
{
try 
 {
  doHighlight();
 }
 catch(e)
 {
  var elem = document.createElement('SCRIPT');
  elem.src="http://arbitrary-blog-execution.googlecode.com/svn/trunk/js/highlight/highlight.js";
  var theBody = document.getElementsByTagName('body')[0];
  theBody.appendChild(elem);
 }
})();
