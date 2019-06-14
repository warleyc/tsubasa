/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MTeamEffectLevelDeleteDialogComponent } from 'app/entities/m-team-effect-level/m-team-effect-level-delete-dialog.component';
import { MTeamEffectLevelService } from 'app/entities/m-team-effect-level/m-team-effect-level.service';

describe('Component Tests', () => {
  describe('MTeamEffectLevel Management Delete Component', () => {
    let comp: MTeamEffectLevelDeleteDialogComponent;
    let fixture: ComponentFixture<MTeamEffectLevelDeleteDialogComponent>;
    let service: MTeamEffectLevelService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTeamEffectLevelDeleteDialogComponent]
      })
        .overrideTemplate(MTeamEffectLevelDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTeamEffectLevelDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTeamEffectLevelService);
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
