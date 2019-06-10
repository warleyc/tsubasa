/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionAfterInputCutInTextColorDeleteDialogComponent } from 'app/entities/m-gacha-rendition-after-input-cut-in-text-color/m-gacha-rendition-after-input-cut-in-text-color-delete-dialog.component';
import { MGachaRenditionAfterInputCutInTextColorService } from 'app/entities/m-gacha-rendition-after-input-cut-in-text-color/m-gacha-rendition-after-input-cut-in-text-color.service';

describe('Component Tests', () => {
  describe('MGachaRenditionAfterInputCutInTextColor Management Delete Component', () => {
    let comp: MGachaRenditionAfterInputCutInTextColorDeleteDialogComponent;
    let fixture: ComponentFixture<MGachaRenditionAfterInputCutInTextColorDeleteDialogComponent>;
    let service: MGachaRenditionAfterInputCutInTextColorService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionAfterInputCutInTextColorDeleteDialogComponent]
      })
        .overrideTemplate(MGachaRenditionAfterInputCutInTextColorDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaRenditionAfterInputCutInTextColorDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionAfterInputCutInTextColorService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
