<?php
define('host', 'localhost');
define('name', 'root');
define('pass', '');
define('dbase', 'lima_smart');

if (mysqli_connect(host,name,pass,dbase)) {
	echo "Connected";
}

$conn = mysqli_connect(host,name,pass,dbase) or die('Unable to connect');



?>