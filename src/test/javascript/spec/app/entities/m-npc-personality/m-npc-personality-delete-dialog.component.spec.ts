/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MNpcPersonalityDeleteDialogComponent } from 'app/entities/m-npc-personality/m-npc-personality-delete-dialog.component';
import { MNpcPersonalityService } from 'app/entities/m-npc-personality/m-npc-personality.service';

describe('Component Tests', () => {
  describe('MNpcPersonality Management Delete Component', () => {
    let comp: MNpcPersonalityDeleteDialogComponent;
    let fixture: ComponentFixture<MNpcPersonalityDeleteDialogComponent>;
    let service: MNpcPersonalityService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MNpcPersonalityDeleteDialogComponent]
      })
        .overrideTemplate(MNpcPersonalityDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MNpcPersonalityDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MNpcPersonalityService);
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
