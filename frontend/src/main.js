// 기본 플러그인 Import
import { createApp, h } from "vue";
import store from "./common/lib/store";
import ElementPlus from "./common/lib/element-plus";
import App from "./App.vue";
import VueAxios from "./common/lib/axios";
import axios from "./common/lib/axios";
import i18n from "./common/lib/i18n";
import router from "./common/lib/vue-router";
import { WebRTC } from "vue-webrtc";

// Element UI Components [시작]
import {
  ElAlert,
  ElAside,
  ElAutocomplete,
  ElAvatar,
  ElBacktop,
  ElBadge,
  ElBreadcrumb,
  ElBreadcrumbItem,
  ElButton,
  ElButtonGroup,
  ElCalendar,
  ElCard,
  ElCarousel,
  ElCarouselItem,
  ElCascader,
  ElCascaderPanel,
  ElCheckbox,
  ElCheckboxButton,
  ElCheckboxGroup,
  ElCol,
  ElCollapse,
  ElCollapseItem,
  ElCollapseTransition,
  ElColorPicker,
  ElContainer,
  ElDatePicker,
  ElDialog,
  ElDivider,
  ElDescriptions,
  ElDescriptionsItem,
  ElDrawer,
  ElDropdown,
  ElDropdownItem,
  ElDropdownMenu,
  ElFooter,
  ElForm,
  ElFormItem,
  ElHeader,
  ElIcon,
  ElImage,
  ElInput,
  ElInputNumber,
  ElLink,
  ElMain,
  ElMenu,
  ElMenuItem,
  ElMenuItemGroup,
  ElOption,
  ElOptionGroup,
  ElPageHeader,
  ElPagination,
  ElPopconfirm,
  ElPopover,
  ElPopper,
  ElProgress,
  ElRadio,
  ElRadioButton,
  ElRadioGroup,
  ElRate,
  ElRow,
  ElScrollbar,
  ElSelect,
  ElSkeleton,
  ElSkeletonItem,
  ElSlider,
  ElStep,
  ElSteps,
  ElSubmenu,
  ElSwitch,
  ElTabPane,
  ElEmpty,
  ElTable,
  ElTableColumn,
  ElTabs,
  ElTag,
  ElTimePicker,
  ElTimeSelect,
  ElTimeline,
  ElTimelineItem,
  ElTooltip,
  ElTransfer,
  ElTree,
  ElUpload,
  ElInfiniteScroll,
  ElLoading,
  ElMessage,
  ElMessageBox,
  ElNotification
} from "element-plus";

const components = [
  ElAlert,
  ElAside,
  ElAutocomplete,
  ElAvatar,
  ElBacktop,
  ElBadge,
  ElBreadcrumb,
  ElBreadcrumbItem,
  ElButton,
  ElButtonGroup,
  ElCalendar,
  ElCard,
  ElCarousel,
  ElCarouselItem,
  ElCascader,
  ElCascaderPanel,
  ElCheckbox,
  ElCheckboxButton,
  ElCheckboxGroup,
  ElCol,
  ElCollapse,
  ElCollapseItem,
  ElCollapseTransition,
  ElColorPicker,
  ElContainer,
  ElDatePicker,
  ElDialog,
  ElDivider,
  ElDescriptionsItem,
  ElDescriptions,
  ElDrawer,
  ElDropdown,
  ElDropdownItem,
  ElDropdownMenu,
  ElFooter,
  ElForm,
  ElFormItem,
  ElHeader,
  ElIcon,
  ElImage,
  ElInput,
  ElInputNumber,
  ElLink,
  ElMain,
  ElMenu,
  ElMenuItem,
  ElMenuItemGroup,
  ElOption,
  ElOptionGroup,
  ElPageHeader,
  ElPagination,
  ElPopconfirm,
  ElPopover,
  ElPopper,
  ElProgress,
  ElRadio,
  ElRadioButton,
  ElRadioGroup,
  ElRate,
  ElRow,
  ElScrollbar,
  ElSelect,
  ElSkeleton,
  ElSkeletonItem,
  ElSlider,
  ElStep,
  ElSteps,
  ElSubmenu,
  ElSwitch,
  ElTabPane,
  ElTable,
  ElTableColumn,
  ElEmpty,
  ElTabs,
  ElTag,
  ElTimePicker,
  ElTimeSelect,
  ElTimeline,
  ElTimelineItem,
  ElTooltip,
  ElTransfer,
  ElTree,
  ElUpload,
];

//fort-awesome
import { library } from "@fortawesome/fontawesome-svg-core";
import {
  faSearch,
  faStar as fasStar,
  faCommentDots,
  faHome,
  faPhone,
  faUser,
  faChevronRight,
  faBuilding,
  faMapMarkedAlt,
  faMapMarker,
  faEnvelope,
  faSpinner,
  faUnlock,
  faUserShield,
  faSignOutAlt,
  faUserCircle,
  faUserEdit,
  faEraser,
  faAngleLeft,
  faAngleDoubleRight,
  faAngleDoubleLeft,
  faPaperclip,
  faKey,
  faImage,
  faUpload,
  faSchool,
  faInfoCircle,
  faSignInAlt,
  faUserPlus,
  faComments,
  faFile,
  faCheck,
  faCheckCircle,
  faHeart,
  faVideo,
  faTrashAlt,
  faDoorOpen,
  faImages
} from "@fortawesome/free-solid-svg-icons";
import { faStar as farStar } from "@fortawesome/free-regular-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

library.add(
  faSearch,
  fasStar,
  farStar,
  faCommentDots,
  faHome,
  faPhone,
  faUser,
  faChevronRight,
  faBuilding,
  faMapMarkedAlt,
  faMapMarker,
  faEnvelope,
  faSpinner,
  faUnlock,
  faUserShield,
  faSignOutAlt,
  faUserCircle,
  faUserEdit,
  faEraser,
  faAngleLeft,
  faAngleDoubleRight,
  faAngleDoubleLeft,
  faPaperclip,
  faKey,
  faImage,
  faUpload,
  faSchool,
  faInfoCircle,
  faSignInAlt,
  faUserPlus,
  faComments,
  faFile,
  faCheck,
  faCheckCircle,
  faHeart,
  faVideo,
  faTrashAlt,
  faDoorOpen,
  faImages
);

const plugins = [
  ElInfiniteScroll,
  ElLoading,
  ElMessage,
  ElMessageBox,
  ElNotification
];
// Element UI Components [끝]

const app = createApp({
  render: () => h(App)
});
app.use(ElementPlus);
app.use(VueAxios, axios);
app.use(store);
app.use(i18n);
app.use(router);
app.use(WebRTC);
app.component("font-awesome-icon", FontAwesomeIcon);

components.forEach(component => {
  app.component(component.name, component);
});

plugins.forEach(plugin => {
  app.use(plugin);
});

app.config.globalProperties.$filters = {
  convertDate(datetime) {
    // YYYY-MM-DD -> YYYY년 MM월 DD일
    var dateStr = datetime.split(" ");
    var dateArr = dateStr[0].split("-");
    var date = "";

    date += dateArr[0] + "년 ";
    date += dateArr[1] + "월 ";
    date += dateArr[2] + "일";
    return date;
  },

  convertTime(datetime) {
    // HH:MM:SS -> 오전/오후 HH:MM
    var timeStr = datetime.split(" ");
    var timeArr = timeStr[1].split(":");
    var time = "";
    var hour = parseInt(timeArr[0]);

    if (hour < 12) {
      time += "오전 ";
      time += hour + ":";
    } else {
      time += "오후 ";
      time += hour - 12 + ":";
    }
    time += timeArr[1];
    return time;
  }
};

/* Kakao javascript Key 설정 */

app.mount("#app");
