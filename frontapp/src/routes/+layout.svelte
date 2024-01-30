<script lang="ts">
	import '../app.css';
	import rq from '$lib/rq/rq.svelte'

	const {children}: any = $props();

	$effect(() => {
		rq.initAuth(); 
        rq.msgInfo('환영합니다.');
    });
</script>

<header>
    <ul>
        <li><a href="/member/login">로그인</a></li>
        <li><a href="/post/mylist">내글</a></li>
    </ul>
</header>

<hr class="my-5">

<main>
	{@render children()}
</main>
