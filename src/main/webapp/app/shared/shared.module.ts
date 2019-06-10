import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { TsubasaSharedLibsModule, TsubasaSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [TsubasaSharedLibsModule, TsubasaSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [TsubasaSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaSharedModule {
  static forRoot() {
    return {
      ngModule: TsubasaSharedModule
    };
  }
}
