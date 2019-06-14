/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionTrajectoryCutInDeleteDialogComponent } from 'app/entities/m-gacha-rendition-trajectory-cut-in/m-gacha-rendition-trajectory-cut-in-delete-dialog.component';
import { MGachaRenditionTrajectoryCutInService } from 'app/entities/m-gacha-rendition-trajectory-cut-in/m-gacha-rendition-trajectory-cut-in.service';

describe('Component Tests', () => {
  describe('MGachaRenditionTrajectoryCutIn Management Delete Component', () => {
    let comp: MGachaRenditionTrajectoryCutInDeleteDialogComponent;
    let fixture: ComponentFixture<MGachaRenditionTrajectoryCutInDeleteDialogComponent>;
    let service: MGachaRenditionTrajectoryCutInService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionTrajectoryCutInDeleteDialogComponent]
      })
        .overrideTemplate(MGachaRenditionTrajectoryCutInDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaRenditionTrajectoryCutInDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionTrajectoryCutInService);
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
