/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MStoreReviewUrlDeleteDialogComponent } from 'app/entities/m-store-review-url/m-store-review-url-delete-dialog.component';
import { MStoreReviewUrlService } from 'app/entities/m-store-review-url/m-store-review-url.service';

describe('Component Tests', () => {
  describe('MStoreReviewUrl Management Delete Component', () => {
    let comp: MStoreReviewUrlDeleteDialogComponent;
    let fixture: ComponentFixture<MStoreReviewUrlDeleteDialogComponent>;
    let service: MStoreReviewUrlService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MStoreReviewUrlDeleteDialogComponent]
      })
        .overrideTemplate(MStoreReviewUrlDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MStoreReviewUrlDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MStoreReviewUrlService);
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
