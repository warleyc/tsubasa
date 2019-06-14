/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MMatchFormationDeleteDialogComponent } from 'app/entities/m-match-formation/m-match-formation-delete-dialog.component';
import { MMatchFormationService } from 'app/entities/m-match-formation/m-match-formation.service';

describe('Component Tests', () => {
  describe('MMatchFormation Management Delete Component', () => {
    let comp: MMatchFormationDeleteDialogComponent;
    let fixture: ComponentFixture<MMatchFormationDeleteDialogComponent>;
    let service: MMatchFormationService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMatchFormationDeleteDialogComponent]
      })
        .overrideTemplate(MMatchFormationDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMatchFormationDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMatchFormationService);
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
