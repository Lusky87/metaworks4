<template>
  <div>
    <div v-if="cInfo.layout==='1 column'" data-type="container" :data-preview="cInfo.dataPreview" :data-keditor-title="cInfo.dataKEditorTitle" :data-categories="cInfo.dataCategories">
      <div class="row">
        <div class="col-sm-12" data-type="container-content">
        </div>
      </div>
    </div>

    <div v-if="cInfo.layout==='2 columns 55'" data-type="container" data-preview="snippets/default/preview/row_6_6.png" data-keditor-title="2 columns (50% - 50%)" data-categories="2 columns">
      <div class="row">
        <div class="col-sm-6" data-type="container-content">
        </div>
        <div class="col-sm-6" data-type="container-content">
        </div>
      </div>
    </div>
    <div v-if="cInfo.layout==='2 column 48'" data-type="container" :data-preview="cInfo.dataPreview" :data-keditor-title="cInfo.dataKEditorTitle" :data-categories="cInfo.dataCategories">
      <div class="row">
        <div class="col-sm-4" data-type="container-content">
        </div>
        <div class="col-sm-8" data-type="container-content">
        </div>
      </div>
    </div>

  <div v-if="cInfo.layout==='2 column 84'" data-type="container" :data-preview="cInfo.dataPreview" :data-keditor-title="cInfo.dataKEditorTitle" :data-categories="cInfo.dataCategories">
      <div class="row">
        <div class="col-sm-8" data-type="container-content">
        </div>
        <div class="col-sm-4" data-type="container-content">
        </div>
      </div>
    </div>

  <div v-if="cInfo.layout==='3 column 444'" data-type="container" :data-preview="cInfo.dataPreview" :data-keditor-title="cInfo.dataKEditorTitle" :data-categories="cInfo.dataCategories">
      <div class="row">
        <div class="col-sm-4" data-type="container-content">
        </div>
        <div class="col-sm-4" data-type="container-content">
        </div>
        <div class="col-sm-4" data-type="container-content">
        </div>
      </div>
    </div>

  <div v-if="cInfo.layout==='3 column 363'" data-type="container" :data-preview="cInfo.dataPreview" :data-keditor-title="cInfo.dataKEditorTitle" :data-categories="cInfo.dataCategories">
      <div class="row">
        <div class="col-sm-3" data-type="container-content">
        </div>
        <div class="col-sm-6" data-type="container-content">
        </div>
        <div class="col-sm-3" data-type="container-content">
        </div>
      </div>
    </div>

  <div v-if="cInfo.layout==='4 column'" data-type="container" :data-preview="cInfo.dataPreview" :data-keditor-title="cInfo.dataKEditorTitle" :data-categories="cInfo.dataCategories">
      <div class="row">
        <div class="col-sm-3" data-type="container-content">
        </div>
        <div class="col-sm-3" data-type="container-content">
        </div>
        <div class="col-sm-3" data-type="container-content">
        </div>
        <div class="col-sm-3" data-type="container-content">
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "containers",
  data: () => ({
    cInfo: [
        { layout: "1 column", dataPreview:"snippets/default/preview/row_12.png", dataKEditorTitle:"1 column", dataCategories:"1 column"},
        { layout: "2 column 55", dataPreview:"snippets/default/preview/row_6_6.png", dataKEditorTitle:"2 columns (50% - 50%)", dataCategories:"2 column"},
        { layout: "2 column 48", dataPreview:"snippets/default/preview/row_4_8.png", dataKEditorTitle:"2 columns (33% - 67%)", dataCategories:"2 column"},
        { layout: "2 column 84", dataPreview:"snippets/default/preview/row_8_4.png", dataKEditorTitle:"2 columns (67% - 33%)", dataCategories:"2 column"},
        { layout: "3 column 444" , dataPreview:"snippets/default/preview/row_4_4_4.png", dataKEditorTitle:"3 columns (33% - 33% - 33%)", dataCategories:"3 column"},
        { layout: "3 column 363", dataPreview:"snippets/default/preview/row_3_6_3.png", dataKEditorTitle:"3 columns (25% - 50% - 35%)", dataCategories:"3 column"},
        { layout: "4 column", dataPreview:"snippets/default/preview/row_3_3_3_3.png", dataKEditorTitle:"4 columns (25% - 25% - 25% - 25%)", dataCategories:"4 column"},
    ],
  }),
  async created() {
  },
  methods: {
  }
}
</script>

<style scoped>

</style>