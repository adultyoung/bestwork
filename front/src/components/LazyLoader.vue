<template>

</template>

<script>
    export default {
        name: "LazyLoader",
        beforeMount() {
            window.onscroll = () => {
                if (window.innerWidth >= 1000) {
                    let header = document.getElementById("sticky_header");

                    if (window.pageYOffset > document.getElementById("sticky_header").offsetHeight + document.getElementById("sticky_header").offsetTop) {
                        header.classList.add("sticky");
                    } else {
                        header.classList.remove("sticky");
                    }
                }
            }
        }
    }
</script>

<style scoped>

</style>