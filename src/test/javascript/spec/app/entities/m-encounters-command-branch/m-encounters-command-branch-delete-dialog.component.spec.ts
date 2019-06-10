/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MEncountersCommandBranchDeleteDialogComponent } from 'app/entities/m-encounters-command-branch/m-encounters-command-branch-delete-dialog.component';
import { MEncountersCommandBranchService } from 'app/entities/m-encounters-command-branch/m-encounters-command-branch.service';

describe('Component Tests', () => {
  describe('MEncountersCommandBranch Management Delete Component', () => {
    let comp: MEncountersCommandBranchDeleteDialogComponent;
    let fixture: ComponentFixture<MEncountersCommandBranchDeleteDialogComponent>;
    let service: MEncountersCommandBranchService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MEncountersCommandBranchDeleteDialogComponent]
      })
        .overrideTemplate(MEncountersCommandBranchDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MEncountersCommandBranchDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MEncountersCommandBranchService);
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
