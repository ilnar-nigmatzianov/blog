<template>
    <div>
        <post-list :posts="posts"></post-list>
    </div>
</template>

<script>
    import PostList from 'components/posts/PostList.vue'

    export default {
        components: {
            PostList
        },
        data() {
            return {
                posts: [
                    {
                        id: 1,
                        text: "test post"
                    },
                    {
                        id: 2,
                        text: "test post 2"
                    }
                ]
            }
        }
    }
</script>

<style>

</style>