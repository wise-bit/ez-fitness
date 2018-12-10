
# Author: Satrajit


import sys
import urllib.request
import urllib.parse
import re

query_string = urllib.parse.urlencode({"search_query" : sys.argv[1]})
html_content = urllib.request.urlopen("http://www.youtube.com/results?" + query_string)
search_results = re.findall(r'href=\"\/watch\?v=(.{11})', html_content.read().decode())
link = "http://www.youtube.com/watch?v=" + search_results[0]
print(link)
