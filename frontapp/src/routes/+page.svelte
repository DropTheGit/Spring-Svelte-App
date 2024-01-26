<script>
    $effect(() => {
        window.$('body').append('박지원')
        window.$('body').click(() => {
            window.$('body').append('바보')
        });
    });

</script>

<svelte:head>
    <title>Home</title>
    <meta name="description" content="홈 화면"/>
</svelte:head>

<section class="text-red-500">
    <i class="fa-regular fa-user"></i>메인입니다.
</section>