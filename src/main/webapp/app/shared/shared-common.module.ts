import { NgModule } from '@angular/core';

import { TsubasaSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
  imports: [TsubasaSharedLibsModule],
  declarations: [JhiAlertComponent, JhiAlertErrorComponent],
  exports: [TsubasaSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class TsubasaSharedCommonModule {}
