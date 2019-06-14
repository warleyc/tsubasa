/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTargetRarityGroupUpdateComponent } from 'app/entities/m-target-rarity-group/m-target-rarity-group-update.component';
import { MTargetRarityGroupService } from 'app/entities/m-target-rarity-group/m-target-rarity-group.service';
import { MTargetRarityGroup } from 'app/shared/model/m-target-rarity-group.model';

describe('Component Tests', () => {
  describe('MTargetRarityGroup Management Update Component', () => {
    let comp: MTargetRarityGroupUpdateComponent;
    let fixture: ComponentFixture<MTargetRarityGroupUpdateComponent>;
    let service: MTargetRarityGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTargetRarityGroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTargetRarityGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTargetRarityGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTargetRarityGroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTargetRarityGroup(123);
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
        const entity = new MTargetRarityGroup();
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
