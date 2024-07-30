#!/bin/sh

http POST localhost:8080/reader name="srinesh" email=srinesh@email.com
http POST localhost:8080/reader name="nisala" email=nisala@email.com

http PUT localhost:8080/book/12342341234 isbn=12342341234 title="title 1" author="user 1"
http PUT localhost:8080/book/12342341234 isbn=12342341234 title="title 1" author="user 1"
http PUT localhost:8080/book/12342341235 isbn=12342341235 title="title 2" author="user 2"
http PUT localhost:8080/book/12342341235 isbn=12342341235 title="title 2" author="user 2"
