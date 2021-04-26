<template>
  <div>
    <table class="gridTable">
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
      <tr v-for="(entry, key) in filteredData" v-bind:key="key" @click="doMouseClick(entry)">
        <td v-for="key in columns" v-bind:key="key">
          {{entry[key]}}
        </td>
      </tr>
    </tbody>
  </table>
  </div>
</template>

<script>
import { userBUS } from '@/etc/EventBus';

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
    },
    doMouseClick: function(entry) {
        userBUS.$emit('selectedRow', entry);
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped="">
.gridTable {
  display: block;
  white-space: nowrap;
  overflow-y: scroll;
  width: 100%;
  border: 2px solid #DF6659;
  border-radius: 3px;
  background-color: #FFF;
}
.gridTable thead {
  display: block;
  background-color: #FFF;
  color: rgba(255,255,255,0.66);
}
.gridTable th {
  background-color: #DF6659;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
.gridTable tbody {
  display: block;
  max-height: 600px;
}
.gridTable td {
  background-color: #F9F9F9;
}
.gridTable th, td {
  font-size: 12px;
  padding: 5px 10px;
}
.gridTable th:nth-of-type(1), .gridTable td:nth-of-type(1) { width: 80px; }
.gridTable th:nth-of-type(2), .gridTable td:nth-of-type(2) { width: 120px; }
.gridTable th:nth-of-type(3), .gridTable td:nth-of-type(3) { width: 100px; }
.gridTable th:nth-of-type(4), .gridTable td:nth-of-type(4) { width: 120px; }
.gridTable th:nth-of-type(5), .gridTable td:nth-of-type(5) { width: 120px; }
.gridTable th:nth-of-type(6), .gridTable td:nth-of-type(6) { width: 100px; }
.gridTable th:nth-of-type(7), .gridTable td:nth-of-type(7) { width: 140px; }
.gridTable th:nth-of-type(8), .gridTable td:nth-of-type(8) { width: 150px; }
.gridTable th:nth-of-type(9), .gridTable td:nth-of-type(9) { width: 80px; }
.gridTable th:last-child { width: 80px; }
.gridTable td:last-child { width: calc(80px - 19px); }
.gridTable th.active {
  color: #FFF;
}
.gridTable th.active .arrow {
  opacity: 1;
}
.gridTable .arrow {
  display: inline-block;
  vertical-align: middle;
  width: 0;
  height: 0;
  margin-left: 5px;
  opacity: 0.66;
}
.gridTable .arrow.asc {
  border-left: 4px solid transparent;
  border-right: 4px solid transparent;
  border-bottom: 4px solid #FFF;
}
.gridTable .arrow.dsc {
  border-left: 4px solid transparent;
  border-right: 4px solid transparent;
  border-top: 4px solid #FFF;
}
.gridTable tr:hover td {
  cursor: pointer;
  background-color: #FBCFD0;
}
</style>