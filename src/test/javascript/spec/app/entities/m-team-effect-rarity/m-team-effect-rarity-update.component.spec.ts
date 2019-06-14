/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTeamEffectRarityUpdateComponent } from 'app/entities/m-team-effect-rarity/m-team-effect-rarity-update.component';
import { MTeamEffectRarityService } from 'app/entities/m-team-effect-rarity/m-team-effect-rarity.service';
import { MTeamEffectRarity } from 'app/shared/model/m-team-effect-rarity.model';

describe('Component Tests', () => {
  describe('MTeamEffectRarity Management Update Component', () => {
    let comp: MTeamEffectRarityUpdateComponent;
    let fixture: ComponentFixture<MTeamEffectRarityUpdateComponent>;
    let service: MTeamEffectRarityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTeamEffectRarityUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTeamEffectRarityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTeamEffectRarityUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTeamEffectRarityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTeamEffectRarity(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTeamEffectRarity();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
