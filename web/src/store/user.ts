import { defineStore} from "pinia"

interface State {
    username: string
    profile: string
    access_token: string
    refresh_token: string
}

export const useUserStore = defineStore('user', {
    
    state: (): State => { 
        return {
            username: '',
            profile: '',
            access_token: '',
            refresh_token: '',
        }
    },
    getters: {

    },

    actions: {
      update_user(user: State) {
          this.username = user.username
          this.profile = user.profile
          this.access_token = user.access_token
          this.refresh_token = user.refresh_token
      },
      saveAToken(token: string) {
        window.localStorage.setItem("access_token", token)
      },
      saveRToken(token: string) {
        window.localStorage.setItem("refresh_token", token)
      },

      getAccess(): string {
        if(!window.localStorage.getItem("access_token")) {
          return ""
        }else {
          return window.localStorage.getItem("access_token") || ""
        }
      },

      getRefresh(): string {
        if(!window.localStorage.getItem("refresh_token")) {
          return ""
        }else {
          return window.localStorage.getItem("refresh_token") || ""
        }
      },
    },
  })