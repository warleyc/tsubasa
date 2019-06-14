/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MMovieAssetDeleteDialogComponent } from 'app/entities/m-movie-asset/m-movie-asset-delete-dialog.component';
import { MMovieAssetService } from 'app/entities/m-movie-asset/m-movie-asset.service';

describe('Component Tests', () => {
  describe('MMovieAsset Management Delete Component', () => {
    let comp: MMovieAssetDeleteDialogComponent;
    let fixture: ComponentFixture<MMovieAssetDeleteDialogComponent>;
    let service: MMovieAssetService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMovieAssetDeleteDialogComponent]
      })
        .overrideTemplate(MMovieAssetDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMovieAssetDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMovieAssetService);
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
