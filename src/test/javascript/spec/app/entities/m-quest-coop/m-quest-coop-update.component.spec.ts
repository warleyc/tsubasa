/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestCoopUpdateComponent } from 'app/entities/m-quest-coop/m-quest-coop-update.component';
import { MQuestCoopService } from 'app/entities/m-quest-coop/m-quest-coop.service';
import { MQuestCoop } from 'app/shared/model/m-quest-coop.model';

describe('Component Tests', () => {
  describe('MQuestCoop Management Update Component', () => {
    let comp: MQuestCoopUpdateComponent;
    let fixture: ComponentFixture<MQuestCoopUpdateComponent>;
    let service: MQuestCoopService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestCoopUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MQuestCoopUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MQuestCoopUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MQuestCoopService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MQuestCoop(123);
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
        const entity = new MQuestCoop();
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
