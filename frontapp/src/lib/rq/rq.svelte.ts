import createClient from "openapi-fetch";
import type {components, paths} from "$lib/types/api/v1/schema";
import 'toastr/build/toastr.css'
import toastr from 'toastr';

class Rq {

	getMember() {
        return this.member;
	}
    private member: components["schemas"]["MemberDto"] | null;

    constructor() {
        this.member = null;
    }


    public setLogined(member: components["schemas"]["MemberDto"]) {
        this.member = member;
    }

 
    public msgInfo(message: string) {
        toastr.info(message);
    }

    public apiEndPoints() {
        return createClient<paths>({ baseUrl: "http://localhost:8090" });
    }

    public msgError(message: string){
        toastr.error(message); 
    }

    public isLogin() {
        return this.member !== null;
    }

    public isLogout() {
        return !this.isLogin();
    }

    initAuth() {
        this.apiEndPoints().GET("/api/v1/members/me", {
            credentials: "include"
        })
        .then((response) => {
            const data = response.data;
            const error = response.error; 

            if ( data ) {
                console.log(data.data?.item);
                this.setLogined(data.data?.item ?? {})
            }
        });

	}
}

const rq = new Rq(); 

export default rq; 