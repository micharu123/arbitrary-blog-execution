// I need this for historic reasons... i.e., I do not want to update all of my old
// blog posts to have the latest functionality. Rather, I will change the script
// they import to do what I want.
/*eval(function()
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
})();*/
SyntaxHighlighter.highlight();

