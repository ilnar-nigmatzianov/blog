<template>
    <div class="post-list">
        <span v-for="post in posts" :key="post.id">
            {{ post.text }}
        </span>
    </div>
</template>

<script>
    export default {
        props: ['posts'],
        data() {

        }
    };
</script>

<style>
</style>