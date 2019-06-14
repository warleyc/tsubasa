/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestCoopDetailComponent } from 'app/entities/m-quest-coop/m-quest-coop-detail.component';
import { MQuestCoop } from 'app/shared/model/m-quest-coop.model';

describe('Component Tests', () => {
  describe('MQuestCoop Management Detail Component', () => {
    let comp: MQuestCoopDetailComponent;
    let fixture: ComponentFixture<MQuestCoopDetailComponent>;
    const route = ({ data: of({ mQuestCoop: new MQuestCoop(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestCoopDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MQuestCoopDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestCoopDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mQuestCoop).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
