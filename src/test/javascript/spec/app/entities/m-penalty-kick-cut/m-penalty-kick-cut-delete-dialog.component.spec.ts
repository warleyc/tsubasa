/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MPenaltyKickCutDeleteDialogComponent } from 'app/entities/m-penalty-kick-cut/m-penalty-kick-cut-delete-dialog.component';
import { MPenaltyKickCutService } from 'app/entities/m-penalty-kick-cut/m-penalty-kick-cut.service';

describe('Component Tests', () => {
  describe('MPenaltyKickCut Management Delete Component', () => {
    let comp: MPenaltyKickCutDeleteDialogComponent;
    let fixture: ComponentFixture<MPenaltyKickCutDeleteDialogComponent>;
    let service: MPenaltyKickCutService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPenaltyKickCutDeleteDialogComponent]
      })
        .overrideTemplate(MPenaltyKickCutDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MPenaltyKickCutDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MPenaltyKickCutService);
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
