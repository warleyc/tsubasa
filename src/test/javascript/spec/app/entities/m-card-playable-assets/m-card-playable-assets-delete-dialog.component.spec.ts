/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MCardPlayableAssetsDeleteDialogComponent } from 'app/entities/m-card-playable-assets/m-card-playable-assets-delete-dialog.component';
import { MCardPlayableAssetsService } from 'app/entities/m-card-playable-assets/m-card-playable-assets.service';

describe('Component Tests', () => {
  describe('MCardPlayableAssets Management Delete Component', () => {
    let comp: MCardPlayableAssetsDeleteDialogComponent;
    let fixture: ComponentFixture<MCardPlayableAssetsDeleteDialogComponent>;
    let service: MCardPlayableAssetsService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCardPlayableAssetsDeleteDialogComponent]
      })
        .overrideTemplate(MCardPlayableAssetsDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCardPlayableAssetsDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCardPlayableAssetsService);
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
