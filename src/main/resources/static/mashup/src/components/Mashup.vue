<template>
  <div>
    <div id="kEditor1">
    </div>
    <google-map-setting />
    <audio-setting />
    <photo-setting />
    <text-setting />
    <youtube-setting />
  </div>
</template>

<script>
// import Snippets from '@/components/Snippets';
// import Containers from './Containers';
const axios = require('axios').default;
export default {
  name: 'mash-up',
  props: {
    // value:String,
    value: {
      required: true,
      type: String,
      default: ''
    }
  },
  data: () => ({
    kEditor: null,
  }),
  components: {
    // "snippets":Snippets,
    // Containers
  },
  methods: {
    onchangeKEditor(evt, fnNm) {
      let me = this;
      // alert(fnNm);
      me.value = me.kEditor[0].children[0].innerHTML;
      console.log("save중  ->", fnNm);
      this.$emit('value', me.value);
      this.$emit('change', me.value);
    },
    resetStat() {
      let me = this;
      me.kEditor[0].children[0].innerHTML="";
      this.$emit('value', "");
      this.$emit('change', "");
    }
  },
  mounted() {
    let me = this;
    // let content = localStorage["keditor.editing.content"];
    // console.log("document   ::   ",document.getElementById("keditor-snippets-list"));
    // if (this.value) $('#kEditor1').innerHTML = this.value;
    // if(content) $('#kEditor1').innerHTML(content);
    me.kEditor = $('#kEditor1');
    // if (this.value){
    //   let tempDivElement = document.createElement("div");
    //   tempDivElement.innerHTML = this.value;
    //   me.kEditor.keditor(tempDivElement);
    // } else {
    if (this.value) {
      $(me.kEditor)[0].innerHTML = this.value;
    }

    me.kEditor.keditor({
      // iframeMode: true,
      // snippetsUrl:'./Snippets',
      tabContainersText: '<i class="fa fa-th-list"></i>',
      tabComponentsText: '<i class="fa fa-file"></i>',
      extraTabs: {
        setting: {
          text: '<i class="fa fa-cogs"></i>',
          title: 'Settings',
          content: (
              '<div class="form-horizontal">' +
              '   <button class="btn btn-primary btn-lg" style="width:100%" id="resetBtn">Reset</button>' +
              '</div>'
          )
        }
      },
      niceScrollEnabled: false,
      tabTooltipEnabled: false,
      snippetsTooltipEnabled: false,
      containerSettingEnabled: true,
      onInitSidebar: function (self) {
        const headers = {
          'Content-type': 'html; charset=UTF-8',
          'Accept': '*/*'
        }

        const baseUrl = "http://localhost:8088"
        axios.get(`${baseUrl}/snippets/default/snippets.html`, {headers}).then(function (resp) {
          console.log("axios result", resp);

          self.renderSnippets(resp.data);
          self.initSnippets();
          self.initTabs();
          self.initTabsSwitcher();
          self.initSettingPanel();

          if (self.options.snippetsFilterEnabled) {
            self.initSnippetsFilter('Container');
            self.initSnippetsFilter('Component');
          }

          if (self.options.snippetsTooltipEnabled || self.options.tabTooltipEnabled) {
            self.body.find('#' + self.options.snippetsListId).find('[data-toggle="tooltip"]').tooltip();
          }

          // if (typeof self.options.onReady === 'function') {
          //   self.options.onReady.call(self);
          // }
        })
      },
      onReady: function () {

      },
      containerSettingInitFunction: function (form, keditor) {
        $("#resetBtn").on("click", function (e) {
          me.resetStat();
        })
        console.log("containerSettingInitFunction  : ", form, keditor);
        form.append(
            '<div class="form-horizontal">' +
            '   <h3>Here is Setting Container Panel!</h3>' +
            '</div>'
        );
      },
      onInitComponent: function (comp) {
        me.onchangeKEditor(comp, 'onInitComponent');
      },
      containerSettingShowFunction: function (form, container, keditor) {
        console.log("containerSettingShowFunction : ", form, container, keditor);
      },
      containerSettingHideFunction: function (form, keditor) {
        console.log("containerSettingHideFunction : ", form, keditor);
      },
      onContentChanged: function (event) {
        me.onchangeKEditor(event, 'onContentChanged');
      },
      onComponentChanged: function (event) {
        me.onchangeKEditor(event, 'onComponentChanged');
      },
      initContentAreas: function (self, contentAreas) {
        console.log('initContentAreas', self, contentAreas)
      }
    });
    // }
  }
}
</script>

<style scoped>

</style>