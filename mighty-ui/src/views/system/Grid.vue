<template>
  <div id="gridTable">
    <table>
    <thead>
      <tr>
        <th v-for="key in columns" v-bind:key="key" @click="sortBy(key)" :class="{ active: sortKey == key }">
          {{ key | capitalize }}
          <span class="arrow" :class="sortOrders[key] > 0 ? 'asc' : 'dsc'">
          </span>
        </th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="entry in filteredData" v-bind:key="entry" class="list-row">
        <td v-for="key in columns" v-bind:key="key">
          {{entry[key]}}
        </td>
      </tr>
    </tbody>
  </table>
  </div>
</template>

<script>
export default {
  name: 'Grid',
  props: {
    data: Array,
    columns: Array,
    filterKey: String
  },
  data () {
    const sortOrders = {}
    this.columns.forEach(key => {
      sortOrders[key] = 1
    })
    return {
      sortKey:'',
      sortOrders: sortOrders
    }
  },
  computed: {
    filteredData() {
      const sortKey = this.sortKey
      const filterKey = this.filterKey && this.filterKey.toLowerCase()
      const order = this.sortOrders[sortKey] || 1
      let data = this.data
      if (filterKey) {
        data = data.filter( row => {
          return Object.keys(row).some( key => {
            return String(row[key]).toLowerCase().indexOf(filterKey) > -1
          })
        })
      }
      if (sortKey) {
        data = data.slice().sort( (a, b) => {
          a = a[sortKey]
          b = b[sortKey]
          return (a === b ? 0 : a > b ? 1 : -1) * order
        })
      }
      return data
    }
  },
  filters: {
    capitalize: function (str) {
      return str.charAt(0).toUpperCase() + str.slice(1)
    }
  },
  methods:{
    sortBy: function (key) {
      this.sortKey = key
      this.sortOrders[key] = this.sortOrders[key] * -1
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped="">
#gridTable {padding-top:10px}
table {
  border: 2px solid #DF6659;
  border-radius: 3px;
  background-color: #fff;
}
th {
  background-color: #DF6659;
  color: rgba(255,255,255,0.66);
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
td {
  background-color: #f9f9f9;
}
th, td {
  font-size: 80%;
  min-width: 120px;
  padding: 5px 10px;
}
th.active {
  color: #fff;
}
th.active .arrow {
  opacity: 1;
}
.arrow {
  display: inline-block;
  vertical-align: middle;
  width: 0;
  height: 0;
  margin-left: 5px;
  opacity: 0.66;
}
.arrow.asc {
  border-left: 4px solid transparent;
  border-right: 4px solid transparent;
  border-bottom: 4px solid #fff;
}
.arrow.dsc {
  border-left: 4px solid transparent;
  border-right: 4px solid transparent;
  border-top: 4px solid #fff;
}
.list-row:hover {
  background-color: #E0BBB7;
  cursor: pointer;
}
</style>