<template>
  <div id="gridTable">
    <table>
    <colgroup>
      <col style="width:8%">
      <col style="width:12%">
      <col style="width:18%">
      <col style="width:24%">
      <col style="width:14%">
      <col style="width:24%">
    </colgroup>
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
import { BUS } from '@/router/EventBus';

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
        BUS.$emit('selectedRow', entry);
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
  background-color: #FFF;
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
tr:hover td {
  cursor: pointer;
  background-color: #FBCFD0;
}
td {
  background-color: #F9F9F9;
}
th, td {
  font-size: 80%;
  min-width: 20px;
  padding: 5px 10px;
}
th.active {
  color: #FFF;
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
  border-bottom: 4px solid #FFF;
}
.arrow.dsc {
  border-left: 4px solid transparent;
  border-right: 4px solid transparent;
  border-top: 4px solid #FFF;
}
tr.listRow:hover {
  background-color: #E0BBB7;
  cursor: pointer;
}
td.listCell:hover {
  background-color: #E0BBB7;
  cursor: pointer;
}
</style>