//
// This file is subject to the terms and conditions defined in
// file 'LICENSE.txt', which is part of this source code package.
//

import UIKit

final class ReactViewController : UIViewController {
    
    struct LaunchConfig {
        
        var bundleRoot: String
        var moduleName: String
        var launchOptions: [UIApplication.LaunchOptionsKey: Any]?
    }
    
    let config: LaunchConfig
    
    init(config: LaunchConfig) {
        self.config = config
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    weak var rctView: RCTRootView!
    
    override func loadView() {
        let config = self.config
        
        let bundleURL = RCTBundleURLProvider.sharedSettings()
            .jsBundleURL(forBundleRoot: config.bundleRoot, fallbackResource: nil)!
        let rctView = RCTRootView(
            bundleURL: bundleURL,
            moduleName: config.moduleName,
            initialProperties: nil,
            launchOptions: config.launchOptions)!
        self.rctView = rctView
        
        let view = UIView(frame: UIScreen.main.bounds)
        view.backgroundColor = UIColor.white
        view.addAndConstrainView(rctView)
        
        self.view = view
    }
    
    var bridge: RCTBridge {
        return self.rctView.bridge
    }
}

private extension UIView {
    
    func addAndConstrainView(_ view: UIView) {
        view.translatesAutoresizingMaskIntoConstraints = false
        self.addSubview(view)
        view.leadingAnchor.constraint(equalTo: self.leadingAnchor).isActive = true
        view.trailingAnchor.constraint(equalTo: self.trailingAnchor).isActive = true
        view.topAnchor.constraint(equalTo: self.topAnchor).isActive = true
        view.bottomAnchor.constraint(equalTo: self.bottomAnchor).isActive = true
    }
}
