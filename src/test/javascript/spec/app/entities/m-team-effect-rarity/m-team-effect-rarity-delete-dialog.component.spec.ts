/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MTeamEffectRarityDeleteDialogComponent } from 'app/entities/m-team-effect-rarity/m-team-effect-rarity-delete-dialog.component';
import { MTeamEffectRarityService } from 'app/entities/m-team-effect-rarity/m-team-effect-rarity.service';

describe('Component Tests', () => {
  describe('MTeamEffectRarity Management Delete Component', () => {
    let comp: MTeamEffectRarityDeleteDialogComponent;
    let fixture: ComponentFixture<MTeamEffectRarityDeleteDialogComponent>;
    let service: MTeamEffectRarityService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTeamEffectRarityDeleteDialogComponent]
      })
        .overrideTemplate(MTeamEffectRarityDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTeamEffectRarityDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTeamEffectRarityService);
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
