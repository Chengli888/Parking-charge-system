#ifndef EASYPR_CORE_PLATEJUDGE_H_
#define EASYPR_CORE_PLATEJUDGE_H_

#include "plate.h"
#include "feature.h"
#include "core_func.h"

namespace easypr {

class PlateJudge {
 public:
  static PlateJudge* instance();

  //! 装载SVM模型
  void loadSVM(const char* s);

  //! 对多幅车牌进行SVM判断
  int plateJudge(const std::vector<CPlate> &, std::vector<CPlate> &);

  //! 车牌判断
  int plateJudge(const std::vector<Mat> &, std::vector<Mat> &);

  //! 车牌判断（一副图像）
  int plateJudge(const Mat &inMat, int &result);

 private:
  PlateJudge();

  static PlateJudge* instance_;

  cv::Ptr<ml::SVM> svm_;
};
}

#endif  // EASYPR_CORE_PLATEJUDGE_H_