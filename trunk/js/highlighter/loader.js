// Change elem.src to point to your closure compiled syntax highlighter.
eval(function()
{
try 
 {
        doHighlight();
 }
 catch(e)
 {
  var elem = document.createElement('SCRIPT');
  elem.src="https://code.google.com/p/arbitrary-blog-execution/source/browse/trunk/js/highlighter/closure-highlighter-all.js";
  var theBody = document.getElementsByTagName('body')[0];
  theBody.appendChild(elem);
 }
})();
