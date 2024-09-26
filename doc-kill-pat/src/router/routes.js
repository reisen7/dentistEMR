const Index = () => import('../views/Index.vue');
const Login = () => import('../views/Login.vue');
const Register = () => import('../views/Register.vue');
const Admin = () => import("../views/Admin.vue");
const AdminOperDep = () => import("../views/AdminOperDep.vue");
const AdminOperDepEnter = () => import("../views/AdminOperDepEnter.vue");
const AdminOperDepInfo = () => import("../views/AdminOperDepInfo.vue");
const AdminOperDoc=()=>import("../views/AdminOperDoc.vue")
const AdminOperDocInfoShow = () => import("../views/AdminOperDocInfoShow.vue")
const AdminOperDocEnter = () => import("../views/AdminOperDocEnter.vue")
const AdminOperDocDeUp = () => import("../views/AdminOperDocDeUp.vue")
const AdminOperPat = () => import("../views/AdminOperPat.vue")
const AdminOperPatInfoShow = () => import("../views/AdminOperPatInfoShow.vue")
const AdminOperPatDeUp = () => import("../views/AdminOperPatDeUp.vue")
const AdminOperDrug = () => import("../views/AdminOperDrug.vue")
const AdminOperDrugEnter = () => import("../views/AdminOperDrugEnter.vue")
const AdminOperDrugInfoShow = () => import("../views/AdminOperDrugInfoShow.vue")
const AdminOperDrugDeUp = () => import("../views/AdminOperDrugDeUp.vue")
const AdminOperDevice = () => import("../views/AdminOperDevice.vue")
const AdminOperDeviceEnter = () => import("../views/AdminOperDeviceEnter.vue")
const AdminOperDeviceEdit = () => import("../views/AdminOperDeviceEdit.vue")
const Doctor = () => import("../views/Doctor.vue");
const DoctorOperPat = () => import("../views/DoctorOperPat.vue")

import User from '../views/User.vue'
import UserUpdate from '../views/Userupdate.vue'
import NotFound from '../views/NotFound.vue'
import Case from '../views/Case.vue'
import DoctorCase from '../views/DoctorCase.vue'
import DoctorCaseEdit from '../views/DoctorCaseEdit.vue'
import Advice from '../views/Advice.vue'
import AdviceDetail from '../views/AdviceDetail.vue'
import Diagnose from '../views/Diagnose.vue'
import DiagnoseDetail from '../views/DiagnoseDetail.vue'
import DiagnoseDetailAdd from '../views/DiagnoseDetailAdd.vue'
import DiagnoseDetailEdit from '../views/DiagnoseDetailEdit.vue'
import Appoint from '../views/Appoint.vue'
import About from '../views/About.vue'


const routes = [

    {
        path: '/',
        name:"Index",
        component:Index,
    },
    {
        path: '/Login',
        name:"Login",
        component: Login,
    },
    {
        path: '/Register',
        name:"Register",
        component: Register,
    }, 
    {
        path: '/admin',
        children: [
            {
                path: 'adminPanel',
                name:"adminPanel",
                component: Admin,
            },
            {
                path: 'adminOperDepart',
                name:"adminOperDepart",
                component: AdminOperDep,
            }, {
                path: 'adminOperDepartEnter',
                name:"adminOperDepartEnter",
                component: AdminOperDepEnter,
            }, {
                path: 'adminOperDepInfo',
                name:"adminOperDepInfo",
                component:AdminOperDepInfo,
            }, {
                path: 'adminOperDoc',
                name:"adminOperDoc",
                component:AdminOperDoc
            },
            {
                path: 'adminOperDocInfoShow',
                name:"adminOperDocInfoShow",
                component:AdminOperDocInfoShow
            }
            ,  {
                path: 'adminOperDocEnter',
                name:"adminOperDocEnter",
                component:AdminOperDocEnter
            }
            ,  {
                path: 'adminOperDocDeUp',
                name:"adminOperDocDeUp",
                component:AdminOperDocDeUp
            },
            {
                path: 'adminOperPat',
                name:"adminOperPat",
                component:AdminOperPat
            }
            ,
            {
                path: 'adminOperPatInfoShow',
                name:"adminOperPatInfoShow",
                component:AdminOperPatInfoShow
            },  {
                path: 'adminOperPatDeUp',
                name:"adminOperPatDeUp",
                component:AdminOperPatDeUp
            }
            ,  {
                path: 'adminOperDrug',
                name:"adminOperDrug",
                component:AdminOperDrug
            }
            ,  {
                path: 'adminOperDrugEnter',
                name:"adminOperDrugEnter",
                component:AdminOperDrugEnter
            }
            ,  {
                path: 'adminOperDrugInfoShow',
                name:"adminOperDrugInfoShow",
                component:AdminOperDrugInfoShow
            }
            ,  {
                path: 'adminOperDrugDeUp',
                name:"adminOperDrugDeUp",
                component:AdminOperDrugDeUp
            }
            ,  {
                path: 'adminOperDevice',
                name:"adminOperDevice",
                component:AdminOperDevice
            }
            ,  {
                path: 'adminOperDeviceEnter',
                name:"adminOperDeviceEnter",
                component:AdminOperDeviceEnter
            }
            ,  {
                path: 'adminOperDeviceEdit',
                name:"adminOperDeviceEdit",
                component:AdminOperDeviceEdit
            }
        ]
    }, {
        path: '/pat',
        children: [{
            path: 'user',
            component: User
          }, {
            path: 'userupdate',
            component: UserUpdate
          }, {
            path: 'case',
            component: Case
          }, {
            path: 'advice',
            component: Advice
          }, {
            path: 'advicedetail',
            component: AdviceDetail
          }, {
            path: 'diagnose',
            component: Diagnose
          }, {
            path: 'diagnosedetail',
            component: DiagnoseDetail
          }, {
            path: 'appoint',
            component: Appoint
          }, {
            path: 'about',
            component: About
            }
        ]
    },
    {
        path: '/doctor',
        children: [{
            path: 'Panel',
            name: "Panel",
            component: Doctor,
        }, {
            path: 'DoctorOperPat',
            name: "DoctorOperPat",
            component: DoctorOperPat,
          },{
            path: 'case',
            component: DoctorCase
          },{
            path: 'caseEdit',
            component: DoctorCaseEdit
          },{
            path: 'DiagnoseDetailEdit',
            component: DiagnoseDetailEdit
          },{
            path: 'DiagnoseDetailAdd',
            component: DiagnoseDetailAdd
          }
    
    ]
    },
    {
        //使用正则方式匹配任意路径：404页面
        path: '/:path(.*)',
        component: NotFound
      }
    
];

export default routes
