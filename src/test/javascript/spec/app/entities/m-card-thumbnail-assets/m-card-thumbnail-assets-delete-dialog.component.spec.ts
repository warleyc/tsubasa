/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MCardThumbnailAssetsDeleteDialogComponent } from 'app/entities/m-card-thumbnail-assets/m-card-thumbnail-assets-delete-dialog.component';
import { MCardThumbnailAssetsService } from 'app/entities/m-card-thumbnail-assets/m-card-thumbnail-assets.service';

describe('Component Tests', () => {
  describe('MCardThumbnailAssets Management Delete Component', () => {
    let comp: MCardThumbnailAssetsDeleteDialogComponent;
    let fixture: ComponentFixture<MCardThumbnailAssetsDeleteDialogComponent>;
    let service: MCardThumbnailAssetsService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCardThumbnailAssetsDeleteDialogComponent]
      })
        .overrideTemplate(MCardThumbnailAssetsDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCardThumbnailAssetsDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCardThumbnailAssetsService);
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
