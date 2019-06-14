/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTargetCharacterGroupUpdateComponent } from 'app/entities/m-target-character-group/m-target-character-group-update.component';
import { MTargetCharacterGroupService } from 'app/entities/m-target-character-group/m-target-character-group.service';
import { MTargetCharacterGroup } from 'app/shared/model/m-target-character-group.model';

describe('Component Tests', () => {
  describe('MTargetCharacterGroup Management Update Component', () => {
    let comp: MTargetCharacterGroupUpdateComponent;
    let fixture: ComponentFixture<MTargetCharacterGroupUpdateComponent>;
    let service: MTargetCharacterGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTargetCharacterGroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTargetCharacterGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTargetCharacterGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTargetCharacterGroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTargetCharacterGroup(123);
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
        const entity = new MTargetCharacterGroup();
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
