
const MainStore = {
    namespaced: true,
    state: {
        mTab: 'Hi'
    },
    mutations: {

    },
    actions: {
        actTest() {
            console.log('MainStore: actTest');
        }
    }
}

export default MainStore