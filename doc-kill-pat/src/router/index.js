import {createRouter, createWebHashHistory,createWebHistory} from 'vue-router'
import routes from './routes'

const router = createRouter({
	routes,
    history: createWebHashHistory()
})

export default router
