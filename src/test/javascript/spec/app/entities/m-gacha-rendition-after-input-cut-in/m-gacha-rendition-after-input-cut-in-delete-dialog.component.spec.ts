/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionAfterInputCutInDeleteDialogComponent } from 'app/entities/m-gacha-rendition-after-input-cut-in/m-gacha-rendition-after-input-cut-in-delete-dialog.component';
import { MGachaRenditionAfterInputCutInService } from 'app/entities/m-gacha-rendition-after-input-cut-in/m-gacha-rendition-after-input-cut-in.service';

describe('Component Tests', () => {
  describe('MGachaRenditionAfterInputCutIn Management Delete Component', () => {
    let comp: MGachaRenditionAfterInputCutInDeleteDialogComponent;
    let fixture: ComponentFixture<MGachaRenditionAfterInputCutInDeleteDialogComponent>;
    let service: MGachaRenditionAfterInputCutInService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionAfterInputCutInDeleteDialogComponent]
      })
        .overrideTemplate(MGachaRenditionAfterInputCutInDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaRenditionAfterInputCutInDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionAfterInputCutInService);
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
