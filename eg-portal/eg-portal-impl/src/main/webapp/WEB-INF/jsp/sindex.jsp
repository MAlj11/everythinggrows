<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>万物生长</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="assets/css/main.css" />
	</head>
	<body>

		<!-- Header -->
			<header id="header">
				<!--<div class="logo"><a href="index.html" style="float:left;display:block;">万物生长</a><span style="color:red;margin-top:23px; padding-left:10px;margin-top:8px;display:block;float:left;"></span></div>-->
				<a href="#menu"><span>菜单</span></a>
			</header>

		<!-- Nav -->
			<nav id="menu">
				<ul class="links">
					<li><a href="/index">首页</a></li>
					<li><a href="/register">注册</a></li>
					<li><a href="/login">登陆</a></li>
				</ul>
			</nav>

		<!-- Banner -->
		<!--
			Note: To show a background image, set the "data-bg" attribute below
			to the full filename of your image. This is used in each section to set
			the background image.
		-->
			<section id="banner" class="bg-img" data-bg="eg.jpg">
				<div class="inner">
					<header>
						<h1>万物生长</h1>
					</header>
				</div>
				<a href="#1" class="more">更多</a>
			</section>

		<!-- One -->
			<section id="1" class="wrapper post bg-img" data-bg="sheying.jpg">
				<div class="inner">
					<article class="box">
						<header>
							<h2>摄影</h2>
						</header>
						<div class="content">
							<p>摄影带给我们一些什么呢?是一口新鲜空气、一股强烈的现实味道，它给予事物的几乎是一种实体的表现，是确实的和真理的无法定义的符号，摄影完全更新了人和宇宙的的关系。</p>
							<p>                           ——布拉塞</p>
						</div>
						<footer>
							<a href="http://www.youtiy.com/" class="button alt">更多</a>
						</footer>
					</article>
				</div>
				<a href="#2" class="more">更多</a>
			</section>

		<!-- Two -->
			<section id="2" class="wrapper post bg-img" data-bg="hulianwang.jpg">
				<div class="inner">
					<article class="box">
						<header>
							<h2>互联网</h2>
						</header>
						<div class="content">
							<p>如果学习只在于模仿，那么我们就不会有科学，也不会有技术</p>
							<p>——高尔基</p>
						</div>
						<footer>
							<a href="generic.html" class="button alt">更多</a>
						</footer>
					</article>
				</div>
				<a href="#3" class="more">更多</a>
			</section>

		<!-- Three -->
			<section id="3" class="wrapper post bg-img" data-bg="yingyin.jpg">
				<div class="inner">
					<article class="box">
						<header>
							<h2>影音</h2>
						</header>
						<div class="content">
							<p>不要试图去填满生命的空白，因为，音乐就来自那空白深处</p>
							<p>——泰戈尔</p>
						</div>
						<footer>
							<a href="generic.html" class="button alt">更多</a>
						</footer>
					</article>
				</div>
				<a href="#4" class="more">更多</a>
			</section>

		<!-- Four -->
			<section id="4" class="wrapper post bg-img" data-bg="ganwu.jpg">
				<div class="inner">
					<article class="box">
						<header>
							<h2>感悟</h2>
						</header>
						<div class="content">
							<p>有些人毕生所追求的东西往往是另一些人与生就俱来的东西。而当人生将走到尽头时, 也许必生追求的人得到了所渴望的,而与生俱来的人却失去了他们仅有的</p>
							<p>——鲁迅</p>
						</div>
						<footer>
							<a href="generic.html" class="button alt">更多</a>
						</footer>
					</article>
				</div>
			</section>

		<!-- Footer -->
			<footer id="footer">
				<div class="inner">

					<h2>成为<万物生长>的一员</h2>

					<form action="#" method="post">

						<div class="field half first">
							<label for="email">Email</label>
							<input name="email" id="email" type="email" placeholder="Email">
						</div>
						<div class="field half first">
							<label for="password">设置密码</label>
							<input name="password" id="password" type="password" placeholder="请设置密码">
						</div>
						<div class="field half first">
							<label for="password">确认密码</label>
							<input name="password" id="password" type="password" placeholder="请确认密码">
						</div>
						<div class="field half first">
							<label for="verification">验证码</label>
							<input type="text" name="verification" id="verification" placeholder="请填入验证码">
						</div>
						<form action="#" method="get">
						<ul class="field half">
							<li><input value="发送验证码" class="button alt" type="submit"></li>
						</ul>
						</form>
						<ul class="actions">
							<li><input value="注册" class="button alt" type="submit"></li>
						</ul>
						<ul class="actions">
							<li><input value="已有账号，去登陆" class="button alt" type="submit"></li>
						</ul>
					</form>

					<!--
					   <ul class="icons">
						<li><a href="#" class="icon round fa-qq"><span class="label">QQ</span></a></li>
						<li><a href="#" class="icon round fa-weixin"><span class="label">微信</span></a></li>
						<li><a href="#" class="icon round fa-renren"><span class="label">人人</span></a></li>
					</ul>
				-->

					<div class="copyright">
						&copy; Untitled. Design: <a href="http://www.youtiy.com">万物生长</a><!--. Images: <a href="https://unsplash.com">Unsplash</a>.-->
					</div>

				</div>
			</footer>

			<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.scrolly.min.js"></script>
			<script src="assets/js/jquery.scrollex.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>

	</body>
</html>